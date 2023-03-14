package com.buikhoinguyen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRoleData {
    public long roleId;
    public String roleName;
    public boolean status;
}
