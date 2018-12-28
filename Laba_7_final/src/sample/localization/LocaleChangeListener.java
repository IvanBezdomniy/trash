package sample.localization;

import java.util.Locale;

public interface LocaleChangeListener
{
    void localeHasChanged(Locale newLoc);
    void setResBundleName(String resBundleName);
    void setResBundleString(String resBundleName);
}