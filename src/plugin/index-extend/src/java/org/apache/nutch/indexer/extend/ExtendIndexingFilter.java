/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.nutch.indexer.extend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.nutch.metadata.Nutch;
import org.apache.nutch.parse.Parse;

import org.apache.nutch.indexer.IndexingFilter;
import org.apache.nutch.indexer.IndexingException;
import org.apache.nutch.indexer.NutchDocument;
import org.apache.hadoop.io.Text;

import org.apache.nutch.crawl.CrawlDatum;
import org.apache.nutch.crawl.Inlinks;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;

/** Adds basic searchable fields to a document. */
public class ExtendIndexingFilter implements IndexingFilter {
  public static final Logger LOG = LoggerFactory.getLogger(ExtendIndexingFilter.class);

  private Configuration conf;

  public NutchDocument filter(NutchDocument doc, Parse parse, Text url, CrawlDatum datum, Inlinks inlinks)
    throws IndexingException {

    LOG.info("<---------Extend Nutch Document Start--------->");
    
    doc.add("score", "3.3");
    
    LOG.info("<---------Extend Nutch Document Stop--------->");
    
    return doc;
  }

  public void setConf(Configuration conf) {
    this.conf = conf;
  }

  public Configuration getConf() {
    return this.conf;
  }

}
