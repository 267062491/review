package com.letv.tbtSps.domain.dto;

/**
 * 这个可以作为一个通用的dto
 * Created with IntelliJ IDEA.
 * User: yuguodong
 * Date: 17-4-3
 * Time: 下午11:07
 * To change this template use File | Settings | File Templates.
 */
public class SpsBtbState {

    private String state ;
    private String content ;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
