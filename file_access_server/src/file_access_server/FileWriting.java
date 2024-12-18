/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_access_server;

import java.io.Serializable;

/**
 *
 * @author s-sys
 */
public class FileWriting implements Serializable
{ 
    String userName;
    String content;

    public FileWriting() {}
    

    public FileWriting(String userName, String write) 
    {
        this.userName = userName;
        this.content = write;
    }

}
