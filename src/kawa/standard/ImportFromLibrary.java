// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.ModuleManager;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import java.io.PrintStream;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

// Referenced classes of package kawa.standard:
//            require

public class ImportFromLibrary extends Syntax
{

    private static final String BUILTIN = "";
    private static final String MISSING = null;
    static final String SRFI97Map[][];
    public static final ImportFromLibrary instance = new ImportFromLibrary();
    public String classPrefixPath[] = {
        "", "kawa.lib."
    };

    public ImportFromLibrary()
    {
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        return null;
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeexp, Translator translator)
    {
        Procedure procedure;
        String s;
        Object obj;
        Object obj2;
        Object obj3;
        int l;
        s = null;
        pair = ((Pair) (pair.getCdr()));
        if (!(pair instanceof Pair))
        {
            return false;
        }
        pair = (Pair)pair;
        obj = pair.getCar();
        if (LList.listLength(obj, false) <= 0)
        {
            translator.error('e', "expected <library reference> which must be a list");
            return false;
        }
        pair = ((Pair) (pair.getCdr()));
        procedure = s;
        if (pair instanceof Pair)
        {
            procedure = s;
            if (((Pair)pair).getCar() instanceof Procedure)
            {
                procedure = (Procedure)((Pair)pair).getCar();
            }
        }
        pair = null;
        s = null;
        obj3 = new StringBuffer();
        while (obj instanceof Pair) 
        {
            obj = (Pair)obj;
            Object obj1 = ((Pair) (obj)).getCar();
            obj = ((Pair) (obj)).getCdr();
            if (obj1 instanceof Pair)
            {
                if (pair != null)
                {
                    translator.error('e', (new StringBuilder()).append("duplicate version reference - was ").append(pair).toString());
                }
                pair = ((Pair) (obj1));
                System.err.println((new StringBuilder()).append("import version ").append(obj1).toString());
            } else
            if (obj1 instanceof String)
            {
                if (obj instanceof Pair)
                {
                    translator.error('e', "source specifier must be last elemnt in library reference");
                }
                s = (String)obj1;
            } else
            {
                if (((StringBuffer) (obj3)).length() > 0)
                {
                    ((StringBuffer) (obj3)).append('.');
                }
                ((StringBuffer) (obj3)).append(Compilation.mangleNameIfNeeded(obj1.toString()));
            }
        }
        pair = null;
        if (s != null)
        {
            obj = require.lookupModuleFromSourcePath(s, scopeexp);
            pair = ((Pair) (obj));
            if (obj == null)
            {
                translator.error('e', (new StringBuilder()).append("malformed URL: ").append(s).toString());
                return false;
            }
        }
        obj = ((StringBuffer) (obj3)).toString();
        s = ((String) (obj));
        if (!((String) (obj)).startsWith("srfi."))
        {
            break MISSING_BLOCK_LABEL_710;
        }
        obj3 = Compilation.demangleName(((String) (obj)).substring(5));
        int i = ((String) (obj3)).indexOf('.');
        if (i < 0)
        {
            s = null;
            i = ((String) (obj3)).length();
        } else
        {
            s = ((String) (obj3)).substring(i + 1);
        }
        obj2 = null;
        if (i >= 2) goto _L2; else goto _L1
_L1:
        obj = obj2;
        if (((String) (obj3)).charAt(0) != ':') goto _L3; else goto _L2
_L2:
        l = 1;
_L8:
        if (l != i) goto _L5; else goto _L4
_L4:
        obj = ((String) (obj3)).substring(1, i);
_L3:
        if (obj == null)
        {
            translator.error('e', "SRFI library reference must have the form: (srfi :NNN [name])");
            return false;
        }
        break; /* Loop/switch isn't completed */
_L5:
        obj = obj2;
        if (Character.digit(((String) (obj3)).charAt(l), 10) < 0) goto _L3; else goto _L6
_L6:
        l++;
        if (true) goto _L8; else goto _L7
_L7:
        int j = SRFI97Map.length;
        do
        {
            l = j - 1;
            if (l < 0)
            {
                translator.error('e', (new StringBuilder()).append("unknown SRFI number '").append(((String) (obj))).append("' in SRFI library reference").toString());
                return false;
            }
            j = l;
        } while (!SRFI97Map[l][0].equals(obj));
        String s2 = SRFI97Map[l][1];
        String s1 = SRFI97Map[l][2];
        if (s != null && !s.equals(s2))
        {
            translator.error('w', (new StringBuilder()).append("the name of SRFI ").append(((String) (obj))).append(" should be '").append(s2).append('\'').toString());
        }
        if (s1 == "")
        {
            return true;
        }
        if (s1 == MISSING)
        {
            translator.error('e', (new StringBuilder()).append("sorry - Kawa does not support SRFI ").append(((String) (obj))).append(" (").append(s2).append(')').toString());
            return false;
        }
        s = s1;
        int k;
        l = classPrefixPath.length;
        k = 0;
_L10:
        if (k >= l)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = (new StringBuilder()).append(classPrefixPath[k]).append(s).toString();
        obj = ModuleManager.getInstance().findWithClassName(((String) (obj)));
        pair = ((Pair) (obj));
_L11:
        k++;
        if (true) goto _L10; else goto _L9
        Exception exception;
        exception;
          goto _L11
_L9:
        if (pair == null)
        {
            translator.error('e', (new StringBuilder()).append("unknown class ").append(s).toString());
            return false;
        } else
        {
            require.importDefinitions(null, pair, procedure, vector, scopeexp, translator);
            return true;
        }
    }

    static 
    {
        String as[] = {
            "2", "and-let*", "gnu.kawa.slib.srfi2"
        };
        String s = MISSING;
        String as1[] = {
            "8", "receive", ""
        };
        String as2[] = {
            "9", "records", ""
        };
        String as3[] = {
            "11", "let-values", ""
        };
        String as4[] = {
            "14", "char-sets", MISSING
        };
        String as5[] = {
            "16", "case-lambda", ""
        };
        String s1 = MISSING;
        String s2 = MISSING;
        String as6[] = {
            "21", "real-time-multithreading", MISSING
        };
        String as7[] = {
            "23", "error", ""
        };
        String as8[] = {
            "25", "multi-dimensional-arrays", ""
        };
        String as9[] = {
            "26", "cut", ""
        };
        String as10[] = {
            "27", "random-bits", MISSING
        };
        String as11[] = {
            "28", "basic-format-strings", ""
        };
        String as12[] = {
            "29", "localization", MISSING
        };
        String as13[] = {
            "31", "rec", MISSING
        };
        String s3 = MISSING;
        String s4 = MISSING;
        String as14[] = {
            "42", "eager-comprehensions", MISSING
        };
        String s5 = MISSING;
        String as15[] = {
            "44", "collections", MISSING
        };
        String as16[] = {
            "45", "lazy", MISSING
        };
        String s6 = MISSING;
        String as17[] = {
            "47", "arrays", MISSING
        };
        String s7 = MISSING;
        String s8 = MISSING;
        String s9 = MISSING;
        String s10 = MISSING;
        String as18[] = {
            "59", "vicinities", MISSING
        };
        String as19[] = {
            "60", "integer-bits", MISSING
        };
        String s11 = MISSING;
        String as20[] = {
            "63", "arrays", MISSING
        };
        String as21[] = {
            "64", "testing", "gnu.kawa.slib.testing"
        };
        String as22[] = {
            "66", "octet-vectors", MISSING
        };
        String s12 = MISSING;
        String as23[] = {
            "71", "let", MISSING
        };
        String as24[] = {
            "74", "blobs", MISSING
        };
        String s13 = MISSING;
        String s14 = MISSING;
        String s15 = MISSING;
        String as25[] = {
            "95", "sorting-and-merging", "kawa.lib.srfi95"
        };
        SRFI97Map = (new String[][] {
            new String[] {
                "1", "lists", "gnu.kawa.slib.srfi1"
            }, as, new String[] {
                "5", "let", s
            }, new String[] {
                "6", "basic-string-ports", ""
            }, as1, as2, as3, new String[] {
                "13", "strings", "gnu.kawa.slib.srfi13"
            }, as4, as5, new String[] {
                "17", "generalized-set!", ""
            }, new String[] {
                "18", "multithreading", s1
            }, new String[] {
                "19", "time", s2
            }, as6, as7, as8, as9, as10, as11, as12, as13, new String[] {
                "38", "with-shared-structure", s3
            }, new String[] {
                "39", "parameters", ""
            }, new String[] {
                "41", "streams", s4
            }, as14, new String[] {
                "43", "vectors", s5
            }, as15, as16, new String[] {
                "46", "syntax-rules", s6
            }, as17, new String[] {
                "48", "intermediate-format-strings", s7
            }, new String[] {
                "51", "rest-values", s8
            }, new String[] {
                "54", "cat", s9
            }, new String[] {
                "57", "records", s10
            }, as18, as19, new String[] {
                "61", "cond", s11
            }, as20, as21, as22, new String[] {
                "67", "compare-procedures", s12
            }, new String[] {
                "69", "basic-hash-tables", "gnu.kawa.slib.srfi69"
            }, as23, as24, new String[] {
                "78", "lightweight-testing", s13
            }, new String[] {
                "86", "mu-and-nu", s14
            }, new String[] {
                "87", "case", s15
            }, as25
        });
    }
}
