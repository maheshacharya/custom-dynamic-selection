package org.example.services;


import org.hippoecm.frontend.plugin.IPluginContext;
import org.hippoecm.frontend.plugin.Plugin;
import org.hippoecm.frontend.plugin.config.IPluginConfig;
import org.onehippo.forge.selection.frontend.model.ListItem;
import org.onehippo.forge.selection.frontend.model.ValueList;
import org.onehippo.forge.selection.frontend.provider.IValueListProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomValueListService extends Plugin implements IValueListProvider {

    private final static String CONFIG_SOURCE = "source";

    public CustomValueListService(IPluginContext context, IPluginConfig config) {
        super(context, config);

        String name = config.getString(IValueListProvider.SERVICE, "service.valuelist.custom");
        context.registerService(this, name);
    }

    @Override
    public ValueList getValueList(String name, Locale locale) {
        ValueList valuelist = new ValueList();


        valuelist.add(new ListItem("north", "North"));
        valuelist.add(new ListItem("east", "East"));
        valuelist.add(new ListItem("west", "West"));
        valuelist.add(new ListItem("south", "South"));


        return valuelist;
    }

    @Override
    public List<String> getValueListNames() {
        List<String> list = new ArrayList<>(1);
        list.add("values");
        return list;
    }

    @Override
    public ValueList getValueList(IPluginConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("Argument 'config' may not be null");
        }
        return getValueList(config.getString(CONFIG_SOURCE));
    }

    @Override
    public ValueList getValueList(String name) {
        return getValueList(name, null/*locale*/);
    }
}