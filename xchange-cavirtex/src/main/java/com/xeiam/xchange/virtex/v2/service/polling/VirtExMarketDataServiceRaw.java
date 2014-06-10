/**
 * Copyright (C) 2012 - 2014 Xeiam LLC http://xeiam.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.xeiam.xchange.virtex.v2.service.polling;

import java.io.IOException;
import java.util.List;

import si.mazi.rescu.RestProxyFactory;

import com.xeiam.xchange.ExchangeSpecification;
import com.xeiam.xchange.currency.CurrencyPair;
import com.xeiam.xchange.virtex.v2.VirtEx;
import com.xeiam.xchange.virtex.v2.dto.marketdata.VirtExDepth;
import com.xeiam.xchange.virtex.v2.dto.marketdata.VirtExTicker;
import com.xeiam.xchange.virtex.v2.dto.marketdata.VirtExTrade;

/**
 * <p>
 * Implementation of the market data service for VirtEx
 * </p>
 * <ul>
 * <li>Provides access to various market data values</li>
 * </ul>
 */
public class VirtExMarketDataServiceRaw extends VirtexBasePollingService {

  private final VirtEx virtEx;

  /**
   * Constructor
   * 
   * @param exchangeSpecification The {@link ExchangeSpecification}
   */
  public VirtExMarketDataServiceRaw(ExchangeSpecification exchangeSpecification) {

    super(exchangeSpecification);
    this.virtEx = RestProxyFactory.createProxy(VirtEx.class, exchangeSpecification.getSslUri());
  }

  public VirtExTicker getVirtExTicker(CurrencyPair currencyPair) throws IOException {

    return virtEx.getTicker(currencyPair.baseSymbol, currencyPair.counterSymbol).getTicker();
  }

  public VirtExDepth getVirtExOrderBook(CurrencyPair currencyPair) throws IOException {

    return virtEx.getFullDepth(currencyPair.baseSymbol, currencyPair.counterSymbol).getDepth();
  }

  public List<VirtExTrade> getVirtExTrades(CurrencyPair currencyPair) throws IOException {

    return virtEx.getTrades(currencyPair.baseSymbol, currencyPair.counterSymbol).getTrades();
  }

}
