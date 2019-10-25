package com.de.za;

import org.springframework.stereotype.Service;

import javax.swing.*;

/**
 * @编写人:de
 * @时间:2019/10/21
 * @描述:
 */
@Service
public class interfImpl extends JFrame implements interf {


    @Override
    public void xixi() {
        System.out.println("嘻嘻");
    }
}
