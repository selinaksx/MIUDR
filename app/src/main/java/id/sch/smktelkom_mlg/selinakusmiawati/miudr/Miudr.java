package id.sch.smktelkom_mlg.selinakusmiawati.miudr;

import com.orm.SugarRecord;

/**
 * Created by Shelin on 17/10/2017.
 */

public class Miudr extends SugarRecord{

    String nrp, instructor, branch, area, codejob, category, codecategory, activity, description, start, end;

    public Miudr(String newNRP, String newInst, String newBranch, String newArea, String newActivity, String newCodeJob, String newCategory, String newCodeCategory, String activity, String newStart, String newEnd, String newDesc) {

    }

    public Miudr (String nrp, String instructor, String branch, String area, String codejob, String category, String codecategory, String activity, String desc, String start, String end) {
        this.nrp = nrp;
        this.instructor = instructor;
        this.branch = branch;
        this.area = area;
        this.codejob = codejob;
        this.category = category;
        this.codecategory = codecategory;
        this.activity = activity;
        this.start = start;
        this.end = end;
        this.description = desc;

    }

    public String getNRP(){
        return nrp;
    }

    public void setNRP(String nrp) {
        this.nrp = nrp;
    }

    public String getInst(){
        return instructor;
    }

    public void setInst(String instructor) {
        this.instructor = instructor;
    }

    public String getBranch(){
        return branch;
    }

    public void setBranch (String branch) {
        this.branch = branch;
    }

    public String getArea(){
        return area;
    }

    public void setArea (String area) {
        this.area = area;
    }

    public String getCodejob(){
        return codejob;
    }

    public void setCodejob(String codejob){
        this.codejob = codejob;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getActivity(){
        return activity;
    }

    public void setActivity(String activity){
        this.activity = activity;
    }

    public String getStart(){
        return start;
    }

    public void setStart(String start){
        this.start = start;
    }

    public String getEnd(){
        return end;
    }

    public void setEnd(String end){
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }



}