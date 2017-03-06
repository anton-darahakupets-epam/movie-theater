package com.epam.movietheater.service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.epam.movietheater.domain.User;


public interface UserService extends AbstractDomainObjectService<User> {

    /**
     * Finding user by email
     * 
     * @param email
     *            Email of the user
     * @return found user or <code>null</code>
     */
    @Nullable User getUserByEmail(@Nonnull String email);

}
