//package com.cui.blog.demo.ElasticSearch.EsController;
//
//
//import com.cui.blog.demo.ElasticSearch.EsPojo.Employee;
//import com.cui.blog.demo.ElasticSearch.EsRepository.EmployeeRepository;
//import com.google.gson.Gson;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping(value = "/es")
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    /**
//     * 添加
//     * @return
//     */
//    @RequestMapping(value = "/add")
//    private String add(){
//        Employee employee = new Employee();
//        for (int i = 0;i<5;i++){
//            employee.setId(String.valueOf(i));
//            employee.setFirstName("xuxu");
//            employee.setLastName("zh");
//            employee.setAge(26);
//            employee.setAbout("i am in peking");
//            employeeRepository.save(employee);
//            System.err.println("add a obj");
//        }
//        return "success";
//    }
//
//    /**
//     * 删除
//     * @return
//     */
//    @RequestMapping("delete")
//    public String delete() {
//        Employee employee = employeeRepository.queryEmployeeById("1");
//        employeeRepository.delete(employee);
//        return "success";
//    }
//
//    /**
//     * 局部更新
//     * @return
//     */
//    @RequestMapping("update")
//    public String update() {
//        Employee employee = employeeRepository.queryEmployeeById("1");
//        employee.setFirstName("哈哈");
//        employeeRepository.save(employee);
//        System.err.println("update a obj");
//        return "success";
//    }
//
//
//    /**
//     * 查询
//     * @return
//     */
//    @RequestMapping("query")
//    public Employee query() {
//        Iterable<Employee> accountInfo = employeeRepository.findAll();
//        System.err.println(new Gson().toJson(accountInfo));
//        return accountInfo.iterator().next();
//    }
//
////    @RequestMapping(value = "queryAll")
////    public List<Employee> queryAll(){
////        Iterable<Employee> iterable = employeeRepository.search()
////        List<Employee> list = new ArrayList<>();
////        while(iterable.iterator().hasNext()){
////            list.add(iterable.iterator().next());
////        }
////        return list;
////    }
//}
