package org.vt.kc;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Customer {
    private long id;
    private String name;
    private String serviceRendered;
    private String address;
}
