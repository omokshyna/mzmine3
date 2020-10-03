/*
 * Copyright 2006-2020 The MZmine Development Team
 *
 * This file is part of MZmine.
 *
 * MZmine is free software; you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * MZmine is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MZmine; if not,
 * write to the Free Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301
 * USA
 */

package io.github.mzmine.modules.io.tdfimport.datamodel.sql;

import io.github.mzmine.gui.chartbasics.gui.wrapper.MouseEventWrapper.Type;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class TDFDataTable<EntryKeyType> {

  private static final Logger logger = Logger.getLogger(TDFDataTable.class.getName());
  protected final String table;
  protected final String entryHeader;
  protected final List<TDFDataColumn<?>> columns;
  protected final TDFDataColumn<EntryKeyType> keyList;

  public TDFDataTable(String table, String entryHeader) {
    this.table = table;
    this.entryHeader = entryHeader;
    columns = new ArrayList<>();
    keyList = new TDFDataColumn<EntryKeyType>(entryHeader);
    columns.add(keyList);
  }

  public void addColumn(@Nonnull TDFDataColumn<?> column) {
    assert column != null;
    columns.add(column);
  }

  @Nullable
  public TDFDataColumn<?> getColumn(String columnName) {
    for (TDFDataColumn<?> column : columns) {
      if (column.coulumnName.equals(columnName)) {
        return column;
      }
    }
    return null;
  }

  protected String getColumnHeadersForQuery() {
    String headers = new String();
    for (TDFDataColumn col : columns) {
      headers += col.getCoulumnName() + ", ";
    }
    headers = headers.substring(0, headers.length() - 2);
    return headers;
  }

  public boolean isValid() {
    long numKeys = keyList.size();
    for(TDFDataColumn<?> col : columns) {
      if(numKeys != col.size()) {
        return false;
      }
    }
    return true;
  }

  public boolean executeQuery(Connection connection) {
    try {
      Statement statement = connection.createStatement();

      String headers = getColumnHeadersForQuery();
      statement.setQueryTimeout(30);
      if (headers == null || headers.isEmpty()) {
        return false;
      }

      String request = getQueryText(getColumnHeadersForQuery());
      ResultSet rs = statement.executeQuery(request);
      int types[] = new int[rs.getMetaData().getColumnCount()];
      if (types.length != columns.size()) {
        logger.info("Number of retrieved columns does not match number of queried columns.");
        return false;
      }
      for (int i = 0; i < types.length; i++) {
        types[i] = rs.getMetaData().getColumnType(i + 1);
      }

      while (rs.next()) {
        for (int i = 0; i < columns.size(); i++) {
          switch (types[i]) {
            case Types.CHAR, Types.LONGNVARCHAR, Types.LONGVARCHAR, Types.NCHAR,
                Types.NVARCHAR, Types.VARCHAR:
              ((TDFDataColumn<String>) columns.get(i)).add(rs.getString(i + 1));
              break;
            case Types.INTEGER, Types.BIGINT, Types.SMALLINT, Types.TINYINT:
              // Bruker stores every natural number value as INTEGER in the sql database
              // Maximum size of INTEGER in SQLite: 8 bytes (64 bits) - https://sqlite.org/datatype3.html
              // So we treat everything as long (64 bit) to be on the save side.
              // this will consume more memory, though
              // However, the .dll's methods want long as argument, anyway. Otherwise we'd have to
              // cast there
              ((TDFDataColumn<Long>) columns.get(i)).add(rs.getLong(i + 1));
              break;
            case Types.DOUBLE, Types.REAL, Types.FLOAT:
              ((TDFDataColumn<Double>) columns.get(i)).add(rs.getDouble(i + 1));
              break;
            default:
              logger.info("Unsupported type loaded in " + table + " " + i + " " + types[i]);
              break;
          }
        }
      }
      rs.close();
      statement.close();
//      print();
//      logger.info("Recieved " + columns.size() + " * " + keyList.getEntries().size() + " entries.");
      return true;
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return false;
    }
  }

  protected String getQueryText(String columnHeadersForQuery) {
    return "SELECT " + columnHeadersForQuery + " FROM " + table;
  }

  public void print() {
    logger.info("Printing " + table + "\t" + columns.size() + " * " + keyList.size() + " entries.");
    /*for (int i = 0; i < keyList.getEntries().size(); i++) {
      String str = i + "\t";
      for (TDFDataColumn col : columns) {
        str += col.getEntries().get(i) + "\t";
      }
      logger.info(str);
    }*/
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TDFDataTable)) {
      return false;
    }
    TDFDataTable<?> that = (TDFDataTable<?>) o;
    return table.equals(that.table) &&
        entryHeader.equals(that.entryHeader) &&
        columns.equals(that.columns) &&
        keyList.equals(that.keyList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(table, entryHeader, columns, keyList);
  }
}
