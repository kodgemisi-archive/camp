package tr.org.lkd.lyk2015.camp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.org.lkd.lyk2015.camp.repository.UserDao;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    public UserDetailsServiceImpl() {
        System.out.println("UserDetailsServiceImpl.UserDetailsServiceImpl");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails user = this.userDao.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("No such user");
        }

        return user;
    }

}