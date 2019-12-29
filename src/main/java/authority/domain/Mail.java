package authority.domain;

public class Mail {
    public Mail(String sender, String mailContent) {
        this.sender = sender;
        this.mailContent = mailContent;
    }

    private String sender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mail(int id, String sender, String mailContent) {
        this.sender = sender;
        this.id = id;
        this.mailContent = mailContent;
    }

    private int id;

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public String getSender() {
        return sender;
    }

    public String getMailContent() {
        return mailContent;
    }

    private String mailContent;
}
