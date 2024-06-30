package Logic;


public class CheckedBookNode
{
    private int checkedBy;
    private int titleChecked;

    public CheckedBookNode() { }

    public CheckedBookNode(int userNum, int bookNum)
    {
        checkedBy = userNum;
        titleChecked = bookNum;
    }

    public int getCheckedBy()
    {
        return checkedBy;
    }

    public int getTitleChecked()
    {
        return titleChecked;
    }

    public void setCheckedBy(int checkedBy)
    {
        this.checkedBy = checkedBy;
    }

    public void setTitleChecked(int titleChecked)
    {
        this.titleChecked = titleChecked;
    }
}
