// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import java.io.PrintStream;

// Referenced classes of package com.google.appinventor.components.runtime:
//            Form, AndroidViewComponent

class val.fWidth
    implements Runnable
{

    final Form this$0;
    final AndroidViewComponent val$component;
    final int val$fWidth;

    public void run()
    {
        System.err.println("(Form)Width not stable yet... trying again");
        setChildWidth(val$component, val$fWidth);
    }

    ViewComponent()
    {
        this$0 = final_form;
        val$component = androidviewcomponent;
        val$fWidth = I.this;
        super();
    }
}
