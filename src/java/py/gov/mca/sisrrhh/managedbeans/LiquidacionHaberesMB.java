/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  javax.enterprise.context.SessionScoped
 *  javax.inject.Named
 */
package py.gov.mca.sisrrhh.managedbeans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "liquidacionHaberesMB")
@SessionScoped
public class LiquidacionHaberesMB
        implements Serializable {

    @PostConstruct
    public void init() {
    }
}
