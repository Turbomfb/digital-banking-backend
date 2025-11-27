/*
 * Copyright (c) 2025 TechService Engineering Team.
 * All rights reserved.
 *
 * This software is proprietary and confidential. It may not be reproduced,
 * distributed, or transmitted in any form or by any means, including photocopying,
 * recording, or other electronic or mechanical methods, without the prior written
 * permission of TechService Engineering Team.
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 *
 * For any questions regarding this license, please contact:
 * TechService Engineering Team
 * Email: engineering@techservicehub.io
 */
package com.techservices.digitalbanking.walletaccount.domain.request;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class InterBankTransferRequest {

    private String receiverAccountNumber;

    private String senderAccountNumber;

    private String senderName;

    private String receiverName;

    private String receiverBankCode;

    private String narration;

    private BigDecimal amount;
}
