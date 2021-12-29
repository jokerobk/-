package com.rjgc.Service;

import com.rjgc.request.UserRequest;
import com.rjgc.res.TableDTO;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 4:18 PM
 */
public interface UsersService {
    TableDTO retrieveUser(UserRequest userRequest);
}
