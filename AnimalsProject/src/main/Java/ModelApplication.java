package com.example.datatemplate;
import com.example.datatemplate.entity.Role;
import com.example.datatemplate.entity.RoleNameEnum;
import com.example.datatemplate.exception.UserAlreadyExistException;
import com.example.datatemplate.repository.RoleRepository;
import com.example.datatemplate.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;




//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class ModelApplication implements CommandLineRunner {


    public static void main(String[] args) {

        SpringApplication.run(ModelApplication.class, args);
        //ApplicationContext cxt = new ClassPathXmlApplicationContext("spring-allUsers.xml");

        //User user=new User("Victor","Burte","vb@gmail.com","xxx");
        //UserDao udao=(UserDao) cxt.getBean("udao");
        //udao.addUser(user);
        //System.out.println(user);
        //List<User> list=udao.getAllUser();
        //for(int i=0;i<list.size();i++){
            //System.out.println(list.get(i));
        //}

    }
    //@Bean
    //public CommandLineRunner demo(CustomerService customerService) {
        //return new CommandLineRunner() {
            //@Override
            //public void run(String... args) {
                //try{
                    //customerService.register("Victor","Burte","monemail@gmail.com","monsuperpassword");
                //} catch(UserAlreadyExistException e){
                    //System.out.println(e.getMessage());
                //}
                //Iterable<Customer> customers= customerService.findAll();
                //for (Iterator<Customer> it =customers.iterator();it.hasNext();){
                    //Customer currentCustomer=it.next();
                    //System.out.println(currentCustomer.getFirstname()+" "+currentCustomer.getLastname()+" "+currentCustomer.getEmail()+" "+currentCustomer.getPassword());
                //}
            //}
        //};
    //}

    //@Bean
    //public CommandLineRunner demo(CustomerRepository repository) {
        //return new CommandLineRunner() {
            //@Override
            //public void run(String... args) throws Exception {
                //repository.save((new Customer("Victor","Burte")));
                //Iterable<Customer> customers= (Iterable<Customer>) repository.findAll();
                //for (Iterator<Customer> it =customers.iterator();it.hasNext();){
                    //Customer currentCustomer=it.next();
                    //System.out.println(currentCustomer.getFirstname()+" "+currentCustomer.getLastname());
                //}
            //}
        //};
    //}

    @Autowired
    CustomerService customerService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
    try{
        Role roleUser=new Role(RoleNameEnum.USER,"Simple user");
        roleRepository.save(roleUser);
        Role roleAdmin=new Role(RoleNameEnum.ADMIN,"Administrator");
        roleRepository.save(roleAdmin);

        customerService.register("Victor","Burte","vb@gmail.com",encoder.encode("alibaba"),true);
    }catch (UserAlreadyExistException e){
        e.printStackTrace();
    }
    }
}
