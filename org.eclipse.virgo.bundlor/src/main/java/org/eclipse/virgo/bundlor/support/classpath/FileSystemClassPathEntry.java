/*******************************************************************************
 * Copyright (c) 2008, 2010 VMware Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   VMware Inc. - initial contribution
 *******************************************************************************/

package org.eclipse.virgo.bundlor.support.classpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.eclipse.virgo.bundlor.ClassPathEntry;

final class FileSystemClassPathEntry implements ClassPathEntry {

    private static final String UTF_8 = "UTF-8";

    private final File root;

    private final File file;

    public FileSystemClassPathEntry(File root, File file) {
        this.root = root;
        this.file = file;
    }

    public InputStream getInputStream() {
        try {
            return new FileInputStream(this.file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Reader getReader() {
        return new InputStreamReader(getInputStream(), Charset.forName(UTF_8));
    }

    public String getName() {
        return this.file.getAbsolutePath().substring(this.root.getAbsolutePath().length() + 1);
    }

    public boolean isDirectory() {
        return this.file.isDirectory();
    }

    public String toString() {
        return getName();
    }

}
