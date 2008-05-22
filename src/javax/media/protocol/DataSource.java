/*  * Copyright (c) 1999-2001 Sun Microsystems, Inc. All Rights Reserved.  *  * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,  * modify and redistribute this software in source and binary code form,  * provided that i) this copyright notice and license appear on all copies of  * the software; and ii) Licensee does not utilize the software in a manner  * which is disparaging to Sun.  *  * This software is provided "AS IS," without a warranty of any kind. ALL  * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY  * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR  * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE  * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING  * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS  * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,  * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER  * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF  * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE  * POSSIBILITY OF SUCH DAMAGES.  *  * This software is not designed or intended for use in on-line control of  * aircraft, air traffic, aircraft navigation or aircraft communications; or in  * the design, construction, operation or maintenance of any nuclear  * facility. Licensee represents and warrants that it will not use or  * redistribute the Software for such purposes.  */

 
// Source File Name:   DataSource.java

package javax.media.protocol;

import java.io.IOException;
import javax.media.*;

// Referenced classes of package javax.media.protocol:
//            Controls

public abstract class DataSource
    implements Controls, Duration
{

    public DataSource()
    {
        sourceLocator = null;
    }

    public DataSource(MediaLocator source)
    {
        sourceLocator = null;
        setLocator(source);
    }

    public void setLocator(MediaLocator source)
    {
        if(sourceLocator == null)
            sourceLocator = source;
        else
            throw new Error("Locator already set on DataSource.");
    }

    public MediaLocator getLocator()
    {
        return sourceLocator;
    }

    protected void initCheck()
    {
        if(sourceLocator == null)
            throw new Error("Uninitialized DataSource error.");
        else
            return;
    }

    public abstract String getContentType();

    public abstract void connect()
        throws IOException;

    public abstract void disconnect();

    public abstract void start()
        throws IOException;

    public abstract void stop()
        throws IOException;

    public abstract Object getControl(String s);

    public abstract Object[] getControls();

    public abstract Time getDuration();

    MediaLocator sourceLocator;
}