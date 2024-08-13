package leetCode;

public class VersionControl {
    int bad = 4;
    public boolean isBadVersion(int version){

        if (version >= bad){
            return true;
        }else {
            return false;
        }
    }
}
