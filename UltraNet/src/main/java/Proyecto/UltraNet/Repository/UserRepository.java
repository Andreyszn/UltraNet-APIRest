package Proyecto.UltraNet.Repository;

import Proyecto.UltraNet.Model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends CRUDMemory <User> {

    public UserRepository() {
        data.add(new User(1, "Luis", "tocayo@gmail.com", "admin", "admin123"));
        data.add(new User(2, "Andrey", "blaith@hotmail.com", "user", "user123"));
        data.add(new User(3, "Charlie", "Charlie@email.com", "client", "client123"));
        data.add(new User(4, "David", "zander@yopmail.com", "client", "client124"));
    }
    
    @Override
    public User edit(User user) {
        for(User element: data){
            if(element.getId().intValue()==user.getId().intValue()) {
                if(user.getName()!=null){
                    element.setName(user.getName());
                }
                if (user.getType()!=null) {
                    element.setType(user.getType());
                }
                if (user.getEmail() != null) {
                    element.setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    element.setPassword(user.getPassword());
                }
                return element;
            }
        }
        return new User();
    }
}
