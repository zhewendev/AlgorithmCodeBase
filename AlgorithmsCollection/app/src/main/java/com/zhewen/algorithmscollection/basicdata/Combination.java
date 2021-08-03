package com.zhewen.algorithmscollection.basicdata;

/**
 * 经典组合问题：选择一支队伍
 * 比如有5个登山队员，名称为 A,B,C,D和E。想要从这五个队员中选择三个队员去登峰，
 * 这时候如何列出所有的队员组合。（不考虑顺序）
 * todo
 */
public class Combination {
    private char[] persons; //组中可供选择的人员
    private boolean[] selects;  //标记成员是否被选中，选中为true

    public Combination(char[] persons) {
        this.persons = persons;
        selects = new boolean[persons.length];
    }
}
