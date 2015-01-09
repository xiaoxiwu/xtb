package com.xt8.service.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;

import com.xt8.model.User;

public class Test {

	public static void main(String[] args) {
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(null);
		set.add(5);
		for (Integer i : set) {
			System.out.println(i);
		}
		int i=0;
		System.out.println(i);
	}
	
    public List<User> getUser(User user){  
        List<User> list = null;  
        String hql = null;  
        return list;  
    }  

}
