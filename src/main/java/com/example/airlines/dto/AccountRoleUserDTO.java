package com.example.airlines.dto;

import com.example.airlines.model.AccountUser;
import com.example.airlines.model.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Ксласс выводящий  аккаунты
 */
public class AccountRoleUserDTO {

    private int idAccount;
    private String login;
    private String passwordAccount;
    private String email;
    private Set<Role> roles;

    public AccountRoleUserDTO() {
    }

    public AccountRoleUserDTO(int idAccount, String login, String passwordAccount, String email, Set<Role> roles) {
        this.idAccount = idAccount;
        this.login = login;
        this.passwordAccount = passwordAccount;
        this.email = email;
        this.roles = roles;
    }

    public List<AccountRoleUserDTO> accountUserListInAccountRoleUserDTOList(List<AccountUser> accountUserList) {
        if (accountUserList == null) {
            return null;
        }

        List<AccountRoleUserDTO> accountRoleUserDTOList = new ArrayList<>();

        for (AccountUser element : accountUserList) {
            AccountRoleUserDTO accountRoleUserDTO = new AccountRoleUserDTO();
            accountRoleUserDTOList.add(accountRoleUserDTO.accountUserToAccountDTO(element));
        }

        return accountRoleUserDTOList;
    }

    public AccountRoleUserDTO accountUserToAccountDTO(AccountUser accountUser) {
        if (accountUser == null) {
            return null;
        }
        return addValuesInAccountDTO(accountUser);
    }


    public AccountRoleUserDTO addValuesInAccountDTO(AccountUser accountUser) {
        AccountRoleUserDTO accountRoleUserDTO = new AccountRoleUserDTO();
        accountRoleUserDTO.setIdAccount(accountUser.getId());
        accountRoleUserDTO.setLogin(accountUser.getLogin());
        accountRoleUserDTO.setEmail(accountUser.getEmail());
        accountRoleUserDTO.setPasswordAccount(accountUser.getPasswordAccount());
        accountRoleUserDTO.setRoles(accountUser.getRoles());

        return accountRoleUserDTO;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordAccount() {
        return passwordAccount;
    }

    public void setPasswordAccount(String passwordAccount) {
        this.passwordAccount = passwordAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
