package com.hjq.permissions;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.VpnService;
import android.provider.Settings;


import android.text.TextUtils;

import java.util.Set;
import java.util.HashSet;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/XXPermissions
 *    time   : 2022/06/11
 *    desc   : Android 4.0 权限委托实现
 */
class PermissionDelegateImplV14 implements PermissionDelegate {

   @Override
   public boolean isGrantedPermission(Context context, String permission) {
      // 检测通知栏权限
      if (PermissionUtils.equalsPermission(permission, Permission.NOTIFICATION_SERVICE)) {
         return isGrantedNotifyPermission(context);
      }

      // 检测获取使用统计权限
      if (PermissionUtils.equalsPermission(permission, Permission.PACKAGE_USAGE_STATS)) {
         return isGrantedPackagePermission(context);
      }

      // 检测通知栏监听权限
      if (PermissionUtils.equalsPermission(permission, Permission.BIND_NOTIFICATION_LISTENER_SERVICE)) {
         return isGrantedNotificationListenerPermission(context);
      }

      // 检测 VPN 权限
      if (PermissionUtils.equalsPermission(permission, Permission.BIND_VPN_SERVICE)) {
         return isGrantedVpnPermission(context);
      }

      /* ---------------------------------------------------------------------------------------- */

      // 向下兼容 Android 13 新权限
      if (!AndroidVersion.isAndroid13()) {

         if (PermissionUtils.equalsPermission(permission, Permission.POST_NOTIFICATIONS)) {
            return isGrantedNotifyPermission(context);
         }
      }

      /* ---------------------------------------------------------------------------------------- */

      return true;
   }

   @Override
   public boolean isPermissionPermanentDenied(Activity activity, String permission) {
      return false;
   }

   @Override
   public Intent getPermissionIntent(Context context, String permission) {
      if (PermissionUtils.equalsPermission(permission, Permission.NOTIFICATION_SERVICE)) {
         return getNotifyPermissionIntent(context);
      }

      if (PermissionUtils.equalsPermission(permission, Permission.PACKAGE_USAGE_STATS)) {
         return getPackagePermissionIntent(context);
      }

      if (PermissionUtils.equalsPermission(permission, Permission.BIND_NOTIFICATION_LISTENER_SERVICE)) {
         return getNotificationListenerIntent(context);
      }

      if (PermissionUtils.equalsPermission(permission, Permission.BIND_VPN_SERVICE)) {
         return getVpnPermissionIntent(context);
      }

      /* ---------------------------------------------------------------------------------------- */

      // 向下兼容 Android 13 新权限
      if (!AndroidVersion.isAndroid13()) {

         if (PermissionUtils.equalsPermission(permission, Permission.POST_NOTIFICATIONS)) {
            return getNotifyPermissionIntent(context);
         }
      }

      /* ---------------------------------------------------------------------------------------- */

      return PermissionUtils.getApplicationDetailsIntent(context);
   }

   /**
    * 是否有通知栏权限
    */
   private static boolean isGrantedNotifyPermission(Context context) {
      return false; //NotificationManagerCompat.from(context).areNotificationsEnabled();
   }

   /**
    * 获取通知栏权限设置界面意图
    */
   private static Intent getNotifyPermissionIntent(Context context) {
      Intent intent = null;
      if (AndroidVersion.isAndroid8()) {
         intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
         intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
         //intent.putExtra(Settings.EXTRA_CHANNEL_ID, context.getApplicationInfo().uid);
      }
      if (intent == null || !PermissionUtils.areActivityIntent(context, intent)) {
         intent = PermissionUtils.getApplicationDetailsIntent(context);
      }
      return intent;
   }

   /**
    * 是否通知栏监听的权限
    */
   private static boolean isGrantedNotificationListenerPermission(Context context) {
      if (AndroidVersion.isAndroid4_3()) {
         Set<String> packageNames = getEnabledListenerPackages(context);
         return packageNames.contains(context.getPackageName());
      }
      return true;
   }
   
	private static final Object sEnabledNotificationListenersLock = new Object();
    private static Set<String> sEnabledNotificationListenerPackages = new HashSet<>();
	private static String sEnabledNotificationListeners;
	public static Set<String> getEnabledListenerPackages(Context context) {
        Set<String> set;
        String enabledNotificationListeners = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        synchronized (sEnabledNotificationListenersLock) {
            if (enabledNotificationListeners != null) {
                if (!enabledNotificationListeners.equals(sEnabledNotificationListeners)) {
                    String[] components = enabledNotificationListeners.split(":", -1);
                    Set<String> packageNames = new HashSet<>(components.length);
                    for (String component : components) {
                        ComponentName componentName = ComponentName.unflattenFromString(component);
                        if (componentName != null) {
                            packageNames.add(componentName.getPackageName());
                        }
                    }
                    sEnabledNotificationListenerPackages = packageNames;
                    sEnabledNotificationListeners = enabledNotificationListeners;
                }
            }
            set = sEnabledNotificationListenerPackages;
        }
        return set;
    }
   /**
    * 获取通知监听设置界面意图
    */
   private static Intent getNotificationListenerIntent(Context context) {
      Intent intent = null;
      if (AndroidVersion.isAndroid11()) {
         AndroidManifestInfo androidManifestInfo = PermissionUtils.getAndroidManifestInfo(context);
         AndroidManifestInfo.ServiceInfo serviceInfo = null;
         if (androidManifestInfo != null) {
            for (AndroidManifestInfo.ServiceInfo info : androidManifestInfo.serviceInfoList) {
               if (!TextUtils.equals(info.permission, Permission.BIND_NOTIFICATION_LISTENER_SERVICE)) {
                  continue;
               }

               if (serviceInfo != null) {
                  // 证明有两个这样的 Service，就不跳转到权限详情页了，而是跳转到权限列表页
                  serviceInfo = null;
                  break;
               }

               serviceInfo = info;
            }
         }
         if (serviceInfo != null) {
            intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_DETAIL_SETTINGS);
            intent.putExtra(Settings.EXTRA_NOTIFICATION_LISTENER_COMPONENT_NAME,
                    new ComponentName(context, serviceInfo.name).flattenToString());
            if (!PermissionUtils.areActivityIntent(context, intent)) {
               intent = null;
            }
         }
      }

      if (intent == null) {
         if (AndroidVersion.isAndroid5_1()) {
            intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
         } else {
            // android.provider.Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS
            intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
         }
      }

      if (!PermissionUtils.areActivityIntent(context, intent)) {
         intent = PermissionUtils.getApplicationDetailsIntent(context);
      }
      return intent;
   }

   /**
    * 是否有使用统计权限
    */
   private static boolean isGrantedPackagePermission(Context context) {
      if (AndroidVersion.isAndroid5()) {
         AppOpsManager appOps = (AppOpsManager)
                 context.getSystemService(Context.APP_OPS_SERVICE);
         int mode;
         if (AndroidVersion.isAndroid10()) {
            mode = appOps.unsafeCheckOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                    context.getApplicationInfo().uid, context.getPackageName());
         } else {
            mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                    context.getApplicationInfo().uid, context.getPackageName());
         }
         return mode == AppOpsManager.MODE_ALLOWED;
      }
      return true;
   }

   /**
    * 获取使用统计权限设置界面意图
    */
   private static Intent getPackagePermissionIntent(Context context) {
      Intent intent = null;
      if (AndroidVersion.isAndroid5()) {
         intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
         if (AndroidVersion.isAndroid10()) {
            // 经过测试，只有在 Android 10 及以上加包名才有效果
            // 如果在 Android 10 以下加包名会导致无法跳转
            intent.setData(PermissionUtils.getPackageNameUri(context));
         }
      }
      if (intent == null || !PermissionUtils.areActivityIntent(context, intent)) {
         intent = PermissionUtils.getApplicationDetailsIntent(context);
      }
      return intent;
   }

   /**
    * 是否有 VPN 权限
    */
   private static boolean isGrantedVpnPermission(Context context) {
      return VpnService.prepare(context) == null;
   }

   /**
    * 获取 VPN 权限设置界面意图
    */
   private static Intent getVpnPermissionIntent(Context context) {
      Intent intent = VpnService.prepare(context);
      if (intent == null || !PermissionUtils.areActivityIntent(context, intent)) {
         intent = PermissionUtils.getApplicationDetailsIntent(context);
      }
      return intent;
   }
}
