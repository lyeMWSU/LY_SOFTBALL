package csc445.lye.edu.missouriwestern.ly_softball2;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SoftballSingleton {
    private static SoftballSingleton sSoftballSingleton;

    private ArrayList<Softball> mSoftballs;

    public static SoftballSingleton get(Context context) {
        if (sSoftballSingleton == null) {
            sSoftballSingleton = new SoftballSingleton(context);
        }
        return sSoftballSingleton;
    }

    private SoftballSingleton(Context context) {
        mSoftballs = new ArrayList<>();
    }

    public void addSoftball(Softball c) {
        mSoftballs.add(c);
    }

    public List<Softball> getSoftball() {
        return mSoftballs;
    }

    public Softball getSoftballs(UUID id) {
        for (Softball softball : mSoftballs) {
            if (softball.getId().equals(id)) {
                return softball;
            }
        }
        return null;
    }
}
