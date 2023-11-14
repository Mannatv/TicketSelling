package ca.sheridancollege.assignment3mannatverma.security;
import ca.sheridancollege.assignment3mannatverma.TicketRepository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private TicketRepository ticketRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ca.sheridancollege.assignment3mannatverma.User.User user = ticketRepo.getUserByUsername(username);
        //if the user does not exist throw an exception
        if(user ==null)
        {
            System.out.println("User name not found");
            throw new UsernameNotFoundException("User not found");
        }
        List<String> roles = ticketRepo.getRolesById(user.getUserId());
        //Convert our list of string inot list of granted authorities
        List <GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        for (String role : roles)
        {
            grantList.add(new SimpleGrantedAuthority(role));
        }
        System.out.println(user);
        //create spring user user based on the above information
        User springUser = new User(user.getUserName(),
                user.getEncryptedPassword(), grantList);

        return (UserDetails)springUser;
    }
}
