package com.example;

import net.csdn.ServiceFramwork;
import net.csdn.bootstrap.Application;

/**
 * User: WilliamZhu
 * Date: 12/22/12
 * Time: 5:06 PM
 */
public class MyFirstApplication extends Application {
    public static void main(String[] args) {
        ServiceFramwork.scanService.setLoader(MyFirstApplication.class);
        Application.main(args);
    }
}
