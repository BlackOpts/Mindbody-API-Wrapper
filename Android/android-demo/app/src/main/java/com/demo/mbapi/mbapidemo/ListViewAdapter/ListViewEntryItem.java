package com.demo.mbapi.mbapidemo.ListViewAdapter;

import java.lang.reflect.Type;


public class ListViewEntryItem
{
    public Type Class;
    public String Title;
    public String Description;
    public int Index;

    public String Header;

    public ListViewEntryItem(Type typeClass, String title, String detail, int index)
    {
        Class = typeClass;
        Title = title;
        Description = detail;
        Index = index;

    }

    /**
     * For section header
     */
    public ListViewEntryItem(String header) {

        Header = header;
    }

    /**
     * For More
     */
    public ListViewEntryItem(Type typeClass) {
        Class = typeClass;
    }

    public Boolean isSectionHeader()
    {
        return Header != null;
    }

    public Boolean isMoreContentAction() {
        return Title == null && Header == null;
    }

}
