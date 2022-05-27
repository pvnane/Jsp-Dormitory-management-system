package com.southwind.service;

import com.southwind.dto.SystemAdminDto;

public interface SystemAdminService {
    public SystemAdminDto login(String username,String password);
}
