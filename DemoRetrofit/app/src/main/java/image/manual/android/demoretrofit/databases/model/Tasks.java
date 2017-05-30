package image.manual.android.demoretrofit.databases.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by EDGY on 5/29/2017.
 */

public class Tasks {
    private String id;
    @SerializedName("local_id")
    private String localID;
    private String color;
    private String name;
    private boolean done;
    @SerializedName("payment_per_hour")
    private double paymentPerHour;
    @SerializedName("due_date")
    private Date dueDate;

    public Tasks(String id, String localID, String color, String name, boolean done, double paymentPerHour, Date dueDate) {
        this.id = id;
        this.localID = localID;
        this.color = color;
        this.name = name;
        this.done = done;
        this.paymentPerHour = paymentPerHour;
        this.dueDate = dueDate;
    }

    public String getId() {
        return id;
    }

    public String getLocalID() {
        return localID;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }

    public double getPaymentPerHour() {
        return paymentPerHour;
    }

    public Date getDueDate() {
        return dueDate;
    }

}
