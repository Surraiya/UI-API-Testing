package Utilities;

public enum TestTableIndex {

    NAME(0),
    METHOD(1),
    STATUS(2),
    START_TIME(3),
    END_TIME(4),
    DURATION(5);

    public final int index;

    TestTableIndex(int index){
        this.index = index;
    }
}
