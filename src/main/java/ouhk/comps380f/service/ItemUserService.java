package ouhk.comps380f.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ouhk.comps380f.dao.ItemUserRepository;
import ouhk.comps380f.dao.UserRoleRepository;
import ouhk.comps380f.exception.UserNotFound;
import ouhk.comps380f.model.ItemUser;
import ouhk.comps380f.model.UserRole;

@Service
public class ItemUserService implements UserDetailsService {
    @Resource
    ItemUserRepository itemUserRepo;
    

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        ItemUser itemUser = itemUserRepo.findById(username).orElse(null);
        if (itemUser == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : itemUser.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new User(itemUser.getUsername(), itemUser.getPassword(), authorities);
    }
    
     public ItemUser getItemUser(String username) {  
        return itemUserRepo.findById(username).orElse(null);
    }
     
    @Transactional(rollbackFor = UserNotFound.class)
    public void updateItemUser(String username,String password,String fullname,String phone,String address,String[] roles)
            throws IOException, UserNotFound {
        ItemUser updatedUser = itemUserRepo.findById(username).orElse(null);
        if (updatedUser == null) {
            throw new UserNotFound();
        }

        updatedUser.setUsername(username);
        updatedUser.setPassword(password);
        updatedUser.setFullname(fullname);
        updatedUser.setPhone(phone);
        updatedUser.setAddress(address);
      
        
         List<UserRole> tempRole = new ArrayList<>();
         
        for (String role : roles) {
            tempRole.add(new UserRole(updatedUser,role));
        }
        
        updatedUser.setRoles(tempRole);
        
        


        itemUserRepo.save(updatedUser);
    }
}
