/*  * Copyright (c) 1999-2001 Sun Microsystems, Inc. All Rights Reserved.  *  * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,  * modify and redistribute this software in source and binary code form,  * provided that i) this copyright notice and license appear on all copies of  * the software; and ii) Licensee does not utilize the software in a manner  * which is disparaging to Sun.  *  * This software is provided "AS IS," without a warranty of any kind. ALL  * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY  * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR  * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE  * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING  * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS  * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,  * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER  * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF  * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE  * POSSIBILITY OF SUCH DAMAGES.  *  * This software is not designed or intended for use in on-line control of  * aircraft, air traffic, aircraft navigation or aircraft communications; or in  * the design, construction, operation or maintenance of any nuclear  * facility. Licensee represents and warrants that it will not use or  * redistribute the Software for such purposes.  */

 
// Source File Name:   NetscapeSecurity.java

package jpg2movie.media;

import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Hashtable;

// Referenced classes of package com.sun.media:
//            JMFSecurity

public class NetscapeSecurity
    implements JMFSecurity
{

    private NetscapeSecurity()
    {
        methodArray = new Method[1];
        classArray = new Class[1];
        arguments = new Object[1][0];
    }

    public String getName()
    {
        return "jmf-security-netscape";
    }

    public void requestPermission(Method m[], Class c[], Object args[][], int request)
        throws SecurityException
    {
        if(enablePrivilege == null)
            throw new SecurityException("Cannot request permission");
        m[0] = enablePrivilege;
        c[0] = privilegeManager;
        Object value = table.get(new Integer(request));
        if(value == null)
        {
            throw new SecurityException("Permission previously denied by user " + request);
        } else
        {
            args[0] = (Object[])value;
            return;
        }
    }

    public void requestPermission(Method m[], Class c[], Object args[][], int request, String parameter)
        throws SecurityException
    {
        requestPermission(m, c, args, request);
    }

    public boolean isLinkPermissionEnabled()
    {
        return linkEnabled;
    }

    public void permissionFailureNotification(int permission)
    {
        table.remove(new Integer(permission));
    }

    public void loadLibrary(String name)
        throws UnsatisfiedLinkError
    {
        try
        {
            if(linkEnabled)
                requestPermission(methodArray, classArray, arguments, 64);
            else
                throw new UnsatisfiedLinkError("No LINK privilege");
            methodArray[0].invoke(classArray[0], arguments[0]);
            System.loadLibrary(name);
        }
        catch(Exception e)
        {
            linkEnabled = false;
            permissionFailureNotification(64);
            throw new UnsatisfiedLinkError("Unable to get " + name + " privilege  " + e);
        }
    }

    static Class _mthclass$(String x0)
    {
        try
        {
            return Class.forName(x0);
        }
        catch(ClassNotFoundException x1)
        {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    public static JMFSecurity security = new NetscapeSecurity();
    private static Method enablePrivilege;
    private static Class privilegeManager;
    private static boolean linkEnabled = true;
    private static Object readPropArgs[] = {
        "UniversalPropertyRead"
    };
    private static Object readFileArgs[] = {
        "UniversalFileRead"
    };
    private static Object writeFileArgs[] = {
        "UniversalFileWrite"
    };
    private static Object deleteFileArgs[] = {
        "UniversalFileDelete"
    };
    private static Object threadArgs[] = {
        "UniversalThreadAccess"
    };
    private static Object threadGroupArgs[] = {
        "UniversalThreadGroupAccess"
    };
    private static Object linkArgs[] = {
        "UniversalLinkAccess"
    };
    private static Object connectArgs[] = {
        "UniversalConnectWithRedirect"
    };
    public static Object windowArgs[] = {
        "UniversalTopLevelWindow"
    };
    public static Object multicastArgs[] = {
        "UniversalMulticast"
    };
    private static Hashtable table;
    private Method methodArray[];
    private Class classArray[];
    private Object arguments[][];

    static 
    {
        table = new Hashtable();
        try
        {
            privilegeManager = Class.forName("netscape.security.PrivilegeManager");
            enablePrivilege = privilegeManager.getMethod("enablePrivilege", new Class[] {
                java.lang.String.class
            });
        }
        catch(ClassNotFoundException e)
        {
            System.err.println("NetscapeSecurity: Cannot find class netscape.security.PrivilegeManager");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        table.put(new Integer(1), ((Object) (readPropArgs)));
        table.put(new Integer(2), ((Object) (readFileArgs)));
        table.put(new Integer(4), ((Object) (writeFileArgs)));
        table.put(new Integer(8), ((Object) (deleteFileArgs)));
        table.put(new Integer(16), ((Object) (threadArgs)));
        table.put(new Integer(32), ((Object) (threadGroupArgs)));
        table.put(new Integer(64), ((Object) (linkArgs)));
        table.put(new Integer(128), ((Object) (connectArgs)));
        table.put(new Integer(256), ((Object) (windowArgs)));
        table.put(new Integer(512), ((Object) (multicastArgs)));
    }
}
