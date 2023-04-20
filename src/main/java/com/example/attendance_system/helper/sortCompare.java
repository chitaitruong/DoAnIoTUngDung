package com.example.attendance_system.helper;

import java.util.Comparator;

import com.example.attendance_system.entities.Ngay;

public class sortCompare implements Comparator<Ngay>   
{  
    // Method of this class  
    @Override  
    public int compare(Ngay a, Ngay b)  
    {  
        /* Returns sorted data in ascending order */  
        return a.getNgay().compareTo(b.getNgay());  
    }  
}  
