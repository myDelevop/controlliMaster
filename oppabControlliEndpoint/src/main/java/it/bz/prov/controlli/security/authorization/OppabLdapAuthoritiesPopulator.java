/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.bz.prov.controlli.security.authorization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.naming.directory.SearchControls;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.SpringSecurityLdapTemplate;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import it.bz.prov.controlli.bo.UtenteBO;
import it.bz.prov.controlli.exception.ServiceException;
import it.bz.prov.controlli.iservices.IGestioneUtentiService;
import it.bz.prov.controlli.util.Utils;


/**
 * The default strategy for obtaining user role information from the directory.
 * <p>
 * It obtains roles by performing a search for "groups" the user is a member of.
 * <p>
 * A typical group search scenario would be where each group/role is specified using the <tt>groupOfNames</tt>
 * (or <tt>groupOfUniqueNames</tt>) LDAP objectClass and the user's DN is listed in the <tt>member</tt> (or
 * <tt>uniqueMember</tt>) attribute to indicate that they should be assigned that role. The following LDIF sample has
 * the groups stored under the DN <tt>ou=groups,dc=springframework,dc=org</tt> and a group called "developers" with
 * "ben" and "luke" as members:
 * <pre>
 * dn: ou=groups,dc=springframework,dc=org
 * objectClass: top
 * objectClass: organizationalUnit
 * ou: groups
 *
 * dn: cn=developers,ou=groups,dc=springframework,dc=org
 * objectClass: groupOfNames
 * objectClass: top
 * cn: developers
 * description: Spring Security Developers
 * member: uid=ben,ou=people,dc=springframework,dc=org
 * member: uid=luke,ou=people,dc=springframework,dc=org
 * ou: developer
 * </pre>
 * <p>
 * The group search is performed within a DN specified by the <tt>groupSearchBase</tt> property, which should
 * be relative to the root DN of its <tt>InitialDirContextFactory</tt>. If the search base is null, group searching is
 * disabled. The filter used in the search is defined by the <tt>groupSearchFilter</tt> property, with the filter
 * argument {0} being the full DN of the user. You can also optionally use the parameter {1}, which will be substituted
 * with the username. You can also specify which attribute defines the role name by setting
 * the <tt>groupRoleAttribute</tt> property (the default is "cn").
 * <p>
 * The configuration below shows how the group search might be performed with the above schema.
 * <pre>
 * &lt;bean id="ldapAuthoritiesPopulator"
 *       class="org.springframework.security.providers.ldap.populator.DefaultLdapAuthoritiesPopulator">
 *   &lt;constructor-arg ref="contextSource"/>
 *   &lt;constructor-arg value="ou=groups"/>
 *   &lt;property name="groupRoleAttribute" value="ou"/>
 * &lt;!-- the following properties are shown with their default values -->
 *   &lt;property name="searchSubTree" value="false"/>
 *   &lt;property name="rolePrefix" value="ROLE_"/>
 *   &lt;property name="convertToUpperCase" value="true"/>
 * &lt;/bean>
 * </pre>
 * A search for roles for user "uid=ben,ou=people,dc=springframework,dc=org" would return the single granted authority
 * "ROLE_DEVELOPER".
 * <p>
 * The single-level search is performed by default. Setting the <tt>searchSubTree</tt> property to true will enable
 * a search of the entire subtree under <tt>groupSearchBase</tt>.
 *
 * @author Luke Taylor
 * @version $Id: DefaultLdapAuthoritiesPopulator.java 3035 2008-05-06 13:59:46Z luke_t $
 */
@SuppressWarnings({"rawtypes", "unused", "unchecked"})
@Transactional
public class OppabLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator {
    //~ Static fields/initializers =====================================================================================

    private static final Log logger = LogFactory.getLog(OppabLdapAuthoritiesPopulator.class);
	private static final org.jboss.logging.Logger oppabLogger = Utils.getLogger();

    //~ Instance fields ================================================================================================

    /**
     * A default role which will be assigned to all authenticated users if set
     */
    private GrantedAuthority defaultRole;

    private ContextSource contextSource;

    private SpringSecurityLdapTemplate ldapTemplate;

    private IGestioneUtentiService _utentiService;

    /**
     * Controls used to determine whether group searches should be performed over the full sub-tree from the
     * base DN. Modified by searchSubTree property
     */
    private SearchControls searchControls = new SearchControls();

    /**
     * The ID of the attribute which contains the role name for a group
     */
    private String groupRoleAttribute = "cn";

    /**
     * The base DN from which the search for group membership should be performed
     */
    private String groupSearchBase;

    /**
     * The pattern to be used for the user search. {0} is the user's DN
     */
    private String groupSearchFilter = "(member={0})";

    /**
     * Attributes of the User's LDAP Object that contain role name information.
     */

//    private String[] userRoleAttributes = null;
 
	private String rolePrefix = "ROLE_";
    private boolean convertToUpperCase = true;

    //~ Constructors ===================================================================================================

    /**
     * Constructor for group search scenarios. <tt>userRoleAttributes</tt> may still be
     * set as a property.
     *
     * @param contextSource supplies the contexts used to search for user roles.
     * @param groupSearchBase          if this is an empty string the search will be performed from the root DN of the
     *                                 context factory.
     */
    public OppabLdapAuthoritiesPopulator(ContextSource contextSource, String groupSearchBase) {
        this.setContextSource(contextSource);
        this.setGroupSearchBase(groupSearchBase);
    }

    //~ Methods ========================================================================================================

    /**
     * This method should be overridden if required to obtain any additional
     * roles for the given user (on top of those obtained from the standard
     * search implemented by this class).
     *
     * @param user the context representing the user who's roles are required
     * @return the extra roles which will be merged with those returned by the group search
     */
    protected Set getAdditionalRoles(DirContextOperations user, String username) {
        return null;
    }

    /**
     * Obtains the authorities for the user who's directory entry is represented by
     * the supplied LdapUserDetails object.
     *
     * @param user the user who's authorities are required
     * @return the set of roles granted to the user.
     */
	public final Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations user, String username) {
        String userDn = user.getNameInNamespace();

        if (logger.isDebugEnabled()) {
            logger.debug("Getting authorities for user " + userDn);
        }
        
        oppabLogger.info(this.getClass().getSimpleName() + ": Recupero dei ruoli per l'utente " + userDn);

        Set roles = getGroupMembershipRoles(userDn, username);

        Set extraRoles = getAdditionalRoles(user, username);

        if (extraRoles != null) {
            roles.addAll(extraRoles);
        }

        if (defaultRole != null) {
            roles.add(defaultRole);
        }
      
        return new ArrayList<GrantedAuthority>(roles);
        
    }

    /**
     * Metodo di ricerca dei ruoli dello specifico utente su ldap.
     * Il metodo � stato modificato per consentire la ricerca dei ruoli sul db
     * 
     * @param userDn
     * @param username
     * @return restituisce la lista di ruoli dell'utente
     */
    @Transactional
	public Set getGroupMembershipRoles(String userDn, String username) {
    	
    	Set authorities = new HashSet();
    	
    	// ottengo il bean degli utenti
    	// FacesContext context = FacesContext.getCurrentInstance();
	    // UtenteUIBean utenteBean = (UtenteUIBean) context.getApplication().evaluateExpressionGet(context, "#{usersBean}", Object.class);
        // UtenteBO utente = utenteBean.findUtente(username);
        try{
            UtenteBO utente = _utentiService.findUtenteByUsername(username);
                	// l'utente non � stato trovato
            if(utente == null) return authorities;
            for (String ga : utente.getRoleList()) authorities.add(new SimpleGrantedAuthority(ga));
            oppabLogger.info(this.getClass().getSimpleName() + ": Lettura terminata. Ruoli trovati: " + authorities);
    	return authorities;
        } catch (ServiceException se) {
            oppabLogger.error(this.getClass().getSimpleName() + " - getGroupMembershipRoles: " + se.getMessage());
            return authorities;
        }
    }

    /**
     * restituisce il contextSource
     * @return ContextSource
     */
    protected ContextSource getContextSource() {
        return contextSource;
    }

    /**
     * Set the {@link ContextSource}
     *
     * @param contextSource supplies the contexts used to search for user roles.
     */
    private void setContextSource(ContextSource contextSource) {
        Assert.notNull(contextSource, "contextSource must not be null");
        this.contextSource = contextSource;

        ldapTemplate = new SpringSecurityLdapTemplate(contextSource);
        ldapTemplate.setSearchControls(searchControls);
    }

    /**
     * Set the group search base (name to search under)
     *
     * @param groupSearchBase if this is an empty string the search will be performed from the root DN of the context
     *                        factory.
     */
    private void setGroupSearchBase(String groupSearchBase) {
        Assert.notNull(groupSearchBase, "The groupSearchBase (name to search under), must not be null.");
        this.groupSearchBase = groupSearchBase;
        if (groupSearchBase.length() == 0) {
            logger.info("groupSearchBase is empty. Searches will be performed from the context source base");
        }
    }

    /**
     * restituisce il GroupSearchBase
     * @return String
     */
    protected String getGroupSearchBase() {
        return groupSearchBase;
    }

    /**
     * imposta il flag di conversione dell'upper case
     * @param convertToUpperCase
     */
    public void setConvertToUpperCase(boolean convertToUpperCase) {
        this.convertToUpperCase = convertToUpperCase;
    }

    /**
     * The default role which will be assigned to all users.
     *
     * @param defaultRole the role name, including any desired prefix.
     */
    public void setDefaultRole(String defaultRole) {
        Assert.notNull(defaultRole, "The defaultRole property cannot be set to null");
        this.defaultRole = new SimpleGrantedAuthority(defaultRole);
    }

    /**
     * imposta il groupRoleAttribute
     * @param groupRoleAttribute
     */
    public void setGroupRoleAttribute(String groupRoleAttribute) {
        Assert.notNull(groupRoleAttribute, "groupRoleAttribute must not be null");
        this.groupRoleAttribute = groupRoleAttribute;
    }

    /**
     * imposta il filtro di ricerca per gruppo
     * @param groupSearchFilter
     */
    public void setGroupSearchFilter(String groupSearchFilter) {
        Assert.notNull(groupSearchFilter, "groupSearchFilter must not be null");
        this.groupSearchFilter = groupSearchFilter;
    }

    /**
     * Sets the prefix which will be prepended to the values loaded from the directory.
     * Defaults to "ROLE_" for compatibility with <tt>RoleVoter/tt>.
     */
    public void setRolePrefix(String rolePrefix) {
        Assert.notNull(rolePrefix, "rolePrefix must not be null");
        this.rolePrefix = rolePrefix;
    }

    /**
     * If set to true, a subtree scope search will be performed. If false a single-level search is used.
     *
     * @param searchSubtree set to true to enable searching of the entire tree below the <tt>groupSearchBase</tt>.
     */
    public void setSearchSubtree(boolean searchSubtree) {
        int searchScope = searchSubtree ? SearchControls.SUBTREE_SCOPE : SearchControls.ONELEVEL_SCOPE;
        searchControls.setSearchScope(searchScope);
    }

    public IGestioneUtentiService get_utentiService() {
        return _utentiService;
    }

    public void set_utentiService(IGestioneUtentiService _utentiService) {
        this._utentiService = _utentiService;
    }
}