/*
 * Copyright (c) 2017 the ACRA team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.acra.http;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.acra.config.CoreConfiguration;
import org.acra.sender.HttpSender;
import org.acra.util.UriUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author F43nd1r
 * @since 10.03.2017
 */

public class BinaryHttpRequest extends BaseHttpRequest<Uri> {
    private final Context context;

    public BinaryHttpRequest(@NonNull CoreConfiguration config, @NonNull Context context,
                             @Nullable String login, @Nullable String password, int connectionTimeOut, int socketTimeOut, @Nullable Map<String, String> headers) {
        super(config, context, HttpSender.Method.PUT, login, password, connectionTimeOut, socketTimeOut, headers);
        this.context = context;
    }

    @NonNull
    @Override
    protected String getContentType(@NonNull Context context, @NonNull Uri uri) {
        return UriUtils.getMimeType(context, uri);
    }

    @Override
    protected void write(OutputStream outputStream, @NonNull Uri content) throws IOException {
        UriUtils.copyFromUri(context, outputStream, content);
    }
}
