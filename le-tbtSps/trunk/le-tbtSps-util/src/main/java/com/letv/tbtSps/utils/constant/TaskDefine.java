package com.letv.tbtSps.utils.constant;

/**
 * 任务定义:定义任务类型、任务名称及其对应的表名
 * 
 * @author yuguodong
 * @version 2017-3-20 9:56:08
 * 
 */
public enum TaskDefine {
    /** A任务 */
    A(10, "A任务", "task_tbtSps"),
    /** B任务 */
    B(20, " B任务", "task_tbtSps");

    private final Integer taskType;
    private final String tableName;
    private final String taskName;

    // 构造函数，枚举类型只能为私有
    private TaskDefine(Integer taskType, String taskName, String tableName) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return this.taskName;
    }

    /**
     * @return the taskType
     */
    public Integer getTaskType() {
        return taskType;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @return the taskName
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * get the enum by its taskType value
     * 
     * @param taskType
     * @return
     */
    public static TaskDefine valueOf(Integer taskType) {
        for (TaskDefine type : values()) {
            if (type.taskType.equals(taskType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No matching value for [" + taskType + "]");
    }
}
