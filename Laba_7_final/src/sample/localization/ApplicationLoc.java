package sample.localization;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ApplicationLoc
{
    private List<LocaleChangeListener> listeners = new ArrayList<>();

    public void addListener(LocaleChangeListener listener)
    {
        listeners.add(listener);
    }

    public void changeLocale(Locale newLoc)
    {
        for(LocaleChangeListener l : listeners)
            l.localeHasChanged(newLoc);
    }
}
