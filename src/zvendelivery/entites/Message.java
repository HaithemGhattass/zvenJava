package zvendelivery.entites;

public class Message {
    private int sender_id, recipient_id,id,is_read;
      private String message,created_at;

    public Message() {
    }

    public Message(int sender_id, int recipient_id, int is_read, String message, String created_at) {
        this.sender_id = sender_id;
        this.recipient_id = recipient_id;
        this.is_read = is_read;
        this.message = message;
        this.created_at = created_at;
    }



    public Message(int sender_id, int recipient_id, int id, int is_read, String message, String created_at) {
        this.sender_id = sender_id;
        this.recipient_id = recipient_id;
        this.id = id;
        this.is_read = is_read;
        this.message = message;
        this.created_at = created_at;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(int recipient_id) {
        this.recipient_id = recipient_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_read() {
        return is_read;
    }

    public void setIs_read(int is_read) {
        this.is_read = is_read;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    
   



}
