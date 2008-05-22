/*  * Copyright (c) 1999-2001 Sun Microsystems, Inc. All Rights Reserved.  *  * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,  * modify and redistribute this software in source and binary code form,  * provided that i) this copyright notice and license appear on all copies of  * the software; and ii) Licensee does not utilize the software in a manner  * which is disparaging to Sun.  *  * This software is provided "AS IS," without a warranty of any kind. ALL  * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY  * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR  * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE  * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING  * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS  * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,  * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER  * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF  * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE  * POSSIBILITY OF SUCH DAMAGES.  *  * This software is not designed or intended for use in on-line control of  * aircraft, air traffic, aircraft navigation or aircraft communications; or in  * the design, construction, operation or maintenance of any nuclear  * facility. Licensee represents and warrants that it will not use or  * redistribute the Software for such purposes.  */

 
// Source File Name:   Time.java

package javax.media;

import java.io.Serializable;

public class Time
    implements Serializable
{

    public Time(long nanoseconds)
    {
        this.nanoseconds = nanoseconds;
    }

    public Time(double seconds)
    {
        nanoseconds = secondsToNanoseconds(seconds);
    }

    protected long secondsToNanoseconds(double seconds)
    {
        return (long)(seconds * 1000000000D);
    }

    public long getNanoseconds()
    {
        return nanoseconds;
    }

    public double getSeconds()
    {
        return (double)nanoseconds * 1.0000000000000001E-009D;
    }

    public static final long ONE_SECOND = 0x3b9aca00L;
    public static final Time TIME_UNKNOWN = new Time(0x7ffffffffffffffeL);
    private static final double NANO_TO_SEC = 1.0000000000000001E-009D;
    protected long nanoseconds;

}
