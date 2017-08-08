package com.tencent.qqgamemi.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.protocol.ServerType;
import java.util.List;

public class EnvironmentSelectDialog$Builder
{
  private Context a;
  private EnvironmentSelectDialog b = null;
  private ListView c = null;

  public EnvironmentSelectDialog$Builder(Context paramContext)
  {
    this.a = paramContext;
  }

  private void b(View paramView)
  {
    EnvironmentSelectDialog.a().add(ServerType.y[0].A);
    EnvironmentSelectDialog.a().add(ServerType.y[1].A);
    EnvironmentSelectDialog.a().add(ServerType.y[2].A);
    EnvironmentSelectDialog.a().add(ServerType.y[3].A);
    this.c = ((ListView)paramView.findViewById(ResourceUtil.f("environment_dialog_context")));
    ArrayAdapter localArrayAdapter = new ArrayAdapter(this.a, 17367055, EnvironmentSelectDialog.a());
    this.c.setAdapter(localArrayAdapter);
    this.c.setChoiceMode(1);
    EnvironmentSelectDialog.a = ServerType.a(this.a);
    this.c.setItemChecked(EnvironmentSelectDialog.a().indexOf(EnvironmentSelectDialog.a), true);
    this.c.setOnItemClickListener(new a(this));
  }

  public Builder a(View paramView)
  {
    return this;
  }

  public EnvironmentSelectDialog a()
  {
    View localView = ((LayoutInflater)this.a.getSystemService("layout_inflater")).inflate(ResourceUtil.a("qmi_environment_select_dialog"), null);
    b(localView);
    this.b = new EnvironmentSelectDialog(this.a, ResourceUtil.d("Qmi_Close_Dialog"));
    this.b.setContentView(localView);
    this.b.getWindow().setType(2003);
    return this.b;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.EnvironmentSelectDialog.Builder
 * JD-Core Version:    0.6.0
 */