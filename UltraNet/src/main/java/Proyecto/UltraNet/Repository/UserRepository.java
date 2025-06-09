package Proyecto.UltraNet.Repository;

import Proyecto.UltraNet.Model.User;

public class UserRepository extends CRUDMemory <User> {

    public UserRepository() {
        //aqui agregamos usuarios a lo baboso luego
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
                return element;
            }
        }
        return new User();
    }
}
