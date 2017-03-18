package skylordtools;

public class DownloadException extends Exception{
    public DownloadException(){
        super("Exception occured when downloading!");
    }

    public DownloadException(String msg){
        super(msg);
    }
}
