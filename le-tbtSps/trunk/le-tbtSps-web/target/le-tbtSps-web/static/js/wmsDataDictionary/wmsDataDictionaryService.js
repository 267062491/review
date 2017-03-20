$.extend($, {
    //wms数据字典服务
    wmsDataDictionaryService: {
        //根据数据字典组编码和字典项编码查找对应的字典组或字典项
        get: function (code, itemCode) {
            var dic = wmsDataDictionaries[code];
            if (dic) {
                if (itemCode) {
                    if (dic.items) {
                        return dic.items[itemCode];
                    }
                    else {
                        return null;
                    }
                }
                return dic;
            }
            $.ajax({
                url: "/portal/dataDictionary/get",
                data: {
                    code: code
                },
                dataType: "json",
                async: false,
                success: function (result) {
                    if (!result) {
                        return;
                    }
                    dic = result;
                }
            });
            if (dic) {
                wmsDataDictionaries[code] = dic;
                if (itemCode) {
                    if (dic.items) {
                        return dic.items[itemCode];
                    }
                    else {
                        return null;
                    }
                }
                return dic;
            }
        },
        //根据数据字典组编码和字典项编码查找对应的字典组或字典值
        getValue: function (code, itemCode) {
            var item = get(code, itemCode);
            if (item) {
                return item.value;
            }
        },
        //根据数据字典组编码查找对应的字典项并转换成数组
        getArray: function (code) {
            var dic = $.wmsDataDictionaryService.get(code);
            if (dic.array) {
                return dic.array;
            }
            if (dic && dic.items) {
                var result = [];
                for (var i in dic.items) {
                    var item = dic.items[i];
                    result.push(item);
                }
                result.sort(function (x, y) {
                    var valueX = x.value;
                    var valueY = y.value;
                    if (!isNaN(valueX) && !isNaN(valueY)) {
                        valueX = parseFloat(valueX);
                        valueY = parseFloat(valueY);
                    }
                    if (valueX > valueY) {
                        return 1;
                    }
                    if (valueX < valueY) {
                        return -1;
                    }
                    return 0;
                });
                dic.array = result;
                return result;
            }
        },
        //根据数据字典组编码查找对应的字典项并转换成字典值数组
        getValueArray: function (code) {
            var dic = $.wmsDataDictionaryService.get(code);
            if (dic && dic.items) {
                var result = [];
                for (var i in dic.items) {
                    var item = dic.items[i];
                    result.push(item.value);
                }
                return result;
            }
        },
        //根据数据字典组编码查找对应的字典项并转换成字典项名称数组
        getNameArray: function (code) {
            var dic = $.wmsDataDictionaryService.get(code);
            if (dic && dic.items) {
                var result = [];
                for (var i in dic.items) {
                    var item = dic.items[i];
                    result.push(item.name);
                }
                return result;
            }
        },
        //根据数据字典组编码查找对应的字典项并转换成字典项编码数组
        getItemCodeArray: function (code) {
            var dic = $.wmsDataDictionaryService.get(code);
            if (dic && dic.items) {
                var result = [];
                for (var i in dic.items) {
                    result.push(i);
                }
                return result;
            }
        },
        //根据数据字典组编码和字典项名称反查对应的值对象
        parseName: function (code, name) {
            var dic = $.wmsDataDictionaryService.get(code);
            if (dic && dic.items) {
                for (var i in dic.items) {
                    var item = dic.items[i];
                    if (item) {
                        if (item.name == name) {
                            return item;
                        }
                    }
                }
            }
        },
        //根据数据字典组编码和字典项值反查对应的值对象
        parseValue: function (code, value) {
            var dic = $.wmsDataDictionaryService.get(code);
            if (dic && dic.items) {
                for (var i in dic.items) {
                    var item = dic.items[i];
                    if (item) {
                        if (item.value == value) {
                            return item;
                        }
                    }
                }
            }
        },
        //根据数据字典组编码和字典项值反查对应的值对象
        parse: function (code, value) {
            return $.wmsDataDictionaryService.parseValue(code, value);
        },
        //formatter4JQGridTime: 0,
        //为JQGrid定制的数据格式化显示方法
        formatter4JQGrid: function (cellvalue, options, rowObject) {
            //var date1 = new Date();  //开始时间
            //try {
            if (cellvalue == null || cellvalue == undefined) {
                return "";
            }
            if (options) {
                var colModel = options.colModel;
                if (colModel) {
                    var formatoptions = colModel.formatoptions;
                    if (formatoptions) {
						var dicItem;
                        if(formatoptions.mapping && formatoptions.mapping.hasOwnProperty(cellvalue)){
                            var relValue = formatoptions.mapping[cellvalue];
                            dicItem = $.wmsDataDictionaryService.parseValue(formatoptions.code, relValue);
                        }
                        else{
                            dicItem = $.wmsDataDictionaryService.parseValue(formatoptions.code, cellvalue);
                        }
                        if (dicItem) {
                            var name = dicItem.name;
                            if (name != null && name != undefined) {
                                if (rowObject && formatoptions.field) {
                                    rowObject[formatoptions.field] = name;
                                }
                            }
                            return name;
                        }
                    }
                }
            }
            return cellvalue;
            //}
            //finally {
            //    var date2 = new Date();  //开始时间
            //    var diff = date2 - date1;
            //    $.wmsDataDictionaryService.formatter4JQGridTime += diff;
            //    console.log($.wmsDataDictionaryService.formatter4JQGridTime);
            //}

        }
    }
});