//
//  CraftDProgressBarBuilder.swift
//  CraftDSwiftUI
//
//  Created by Pedro Alvarez on 17/11/25.
//

import SwiftUI

class CraftDProgressBarBuilder: CraftDBuilder {
    public func craft(model: SimpleProperties, listener: @escaping CraftDViewListener) -> any View {
        do {
            let properties = try model.decodeValue(ProgressBarProperties.self, using: decoder)
            return CraftDProgressBar(properties, listener: listener)
        } catch {
            return EmptyView()
        }
    }
}
