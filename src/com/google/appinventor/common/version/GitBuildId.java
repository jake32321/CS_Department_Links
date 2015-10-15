// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.common.version;


public final class GitBuildId
{

    public static final String ACRA_URI = "https://acra2.appinventor.mit.edu/acra/";
    public static final String ANT_BUILD_DATE = "October 1 2015";
    public static final String GIT_BUILD_FINGERPRINT = "0a6023b8f11ea8e358502b95922cc84dd975ec86";
    public static final String GIT_BUILD_VERSION = "nb146a";

    private GitBuildId()
    {
    }

    public static String getAcraUri()
    {
        if ("https://acra2.appinventor.mit.edu/acra/".equals("${acra.uri}"))
        {
            return "";
        } else
        {
            return "https://acra2.appinventor.mit.edu/acra/".trim();
        }
    }

    public static String getDate()
    {
        return "October 1 2015";
    }

    public static String getFingerprint()
    {
        return "0a6023b8f11ea8e358502b95922cc84dd975ec86";
    }

    public static String getVersion()
    {
        String s = "nb146a";
        if ("nb146a" == "" || "nb146a".contains(" "))
        {
            s = "none";
        }
        return s;
    }
}
