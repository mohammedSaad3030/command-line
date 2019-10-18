public class NoSuchFileOrDirectory extends Exception
{
    String msg;
    NoSuchFileOrDirectory(String msg)
    {
        this.msg = msg;
    }

    @Override
    public String getMessage()
    {
        return msg;
    }
}

public class DirectoryExists extends Exception
{
    String msg;
    DirectoryExists(String msg) {
        this.msg = msg;
    }
    @Override
    public String getMessage()
    {
        return msg;
    }
}

public class DirectoryNotEmpty extends Exception {
    String msg;
    DirectoryNotEmpty(String msg){
        this.msg=msg;
    }
    @Override
    public String getMessage() {
        return msg;
    }
}
public class DirectoryWithTheSameName extends Exception
{
    String msg;
    DirectoryWithTheSameName(String msg)
    {
        this.msg = msg;
    }
    @Override
    public String getMessage()
    {
        return msg;
    }
}

