package csc445.lye.edu.missouriwestern.ly_softball2;

import java.util.Date;
import java.util.UUID;

public class Softball {
    private UUID id;
    private String lastName;
    private String firstName;
    private String number;
    private Date lastUpdate;
    private boolean isPitcher;
    private boolean isCatcher;
    private boolean isInfield;
    private boolean isOutfield;
    private String positions;
    private String Title;


    public Softball(){
        setId();
        setLastUpdate();
        setLastName("");
        setFirstName("");
        setNumber(99);
        setPitcher(false);
        setCatcher(false);
        setInfield(false);
        setOutfield(true);
        setPositions();
    }

    public UUID getId(){
        return id;
    }

    public void setId(){
        id = UUID.randomUUID();
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public void setNumber(int number){
        this.number = String.valueOf(number);
    }

    public Date getLastUpdate(){
        return lastUpdate;
    }

    public void setLastUpdate(){
        this.lastUpdate = new Date();
    }

    public boolean isPitcher(){
        return isPitcher;
    }

    public void setPitcher(boolean pitcher){
        isPitcher = pitcher;
        setPositions();
    }

    public boolean isCatcher(){
        return isCatcher;
    }

    public void setCatcher(boolean catcher) {
        isCatcher = catcher;
        setPositions();
    }

    public boolean isInfield(){
        return isInfield;
    }

    public void setInfield(boolean infield){
        isInfield = infield;
        setPositions();
    }

    public boolean isOutfield() {
        return isOutfield;
    }

    public void setOutfield(boolean outfield){
        isOutfield = outfield;
        setPositions();
    }

    public String getPositions(){
        return positions;
    }

    public void setPositions(){
        positions = "";
        if (isPitcher)
            positions += R.string.symbol_pitcher;
        if (isCatcher)
            positions += R.string.symbol_catcher;
        if (isInfield)
            positions += R.string.symbol_infield;
        if (isOutfield)
            positions += R.string.symbol_outfield;
    }

    @Override
    public String toString(){
        return "Player{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", number='" + number + '\'' +
                ", lastUpdate='" + positions + '\'' +
                '}';
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

}