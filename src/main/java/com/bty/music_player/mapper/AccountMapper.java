package com.bty.music_player.mapper;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.bty.music_player.dto.request.AccountCreationRequest;
import com.bty.music_player.dto.request.AccountUpdateRequest;
import com.bty.music_player.dto.response.AccountResponse;
import com.bty.music_player.entity.Account;
import com.bty.music_player.entity.Role;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "accountName", source = "accountName")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "roleNames", source = "roles", qualifiedByName = "rolesToRoleNames")
    AccountResponse toAccountResponse(Account account);
    
    @Named("rolesToRoleNames")
    public static Set<String> toRoleNames(Set<Role> roles) {
        if(roles == null) return new HashSet<String>();
        Set<String> roleNames = new HashSet<String>();
        roles.forEach(role -> {
            roleNames.add(role.getName());
        });
        return roleNames;
    }
    
    @Mapping(target = "accountName", source = "accountName")
    @Mapping(target = "password", source = "password")
    Account toAccount(AccountCreationRequest request);
    
    @Mapping(target = "password", source = "password")
    void update(@MappingTarget Account account, AccountUpdateRequest request);
}
