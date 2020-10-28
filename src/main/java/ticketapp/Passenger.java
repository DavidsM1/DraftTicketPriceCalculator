package ticketapp;

public class Passenger {
    private Boolean isChild;
    private int luggageItemCount;

    public Boolean getChild() {
        return isChild;
    }

    public void setChild(Boolean child) {
        isChild = child;
    }

    public int getLuggageItemCount() {
        return luggageItemCount;
    }

    public void setLuggageItemCount(int luggageItemCount) {
        if (luggageItemCount < 0) {
            throw new IllegalArgumentException("luggage item count can't be negative");
        }
        this.luggageItemCount = luggageItemCount;
    }

}
