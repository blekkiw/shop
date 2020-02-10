package ee.blakcat.Models;

public enum ProcessStatus {
    PROCESS ("In progress"),
    DONE("Done"),
    FAILED("Failed"),
    ACTIVE("Active"),
    TOPAY("To pay");
    public String name;
    ProcessStatus (String name) {
        this.name=name;
    }
}
