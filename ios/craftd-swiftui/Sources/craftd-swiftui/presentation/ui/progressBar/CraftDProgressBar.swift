import SwiftUI

struct CraftDProgressBar: View {
    let text: String
    let textAlign: Alignment
    let alignment: CraftDAlign
    let progressColor: String
    let textColor: String
    let progress: Double
    let height: CGFloat
    let spaceBetween: CGFloat
    let listener: CraftDViewListener
    
    init(
        _ properties: ProgressBarProperties,
        listener: @escaping CraftDViewListener
    ) {
        self.text = properties.text ?? ""
        self.textAlign = properties.textAlign?.alignment ?? .center
        self.progressColor = properties.progressColor ?? "#0000FF"
        self.progress = properties.progress ?? 0.0
        self.height = CGFloat(properties.height ?? 0.0)
        self.alignment = properties.alignment ?? .top
        self.textColor = properties.textColor ?? "#000000"
        self.spaceBetween = properties.spaceBetween ?? 0.0
        self.listener = listener
    }
    
    @ViewBuilder
    private var topAlignmentLayout: some View {
        VStack(spacing: .zero) {
            Text(text)
                .font(.body)
                .foregroundStyle(Color(hex: textColor))
            
            GeometryReader { geometry in
                ZStack(alignment: .leading) {
                    RoundedRectangle(cornerRadius: 16)
                        .stroke(Color.gray.opacity(0.6), lineWidth: 2)
                        .fill(Color.clear)
                        .frame(height: height)
                    
                    RoundedRectangle(cornerRadius: 16)
                        .fill(Color(hex: progressColor))
                        .frame(width: geometry.size.width * progress, height: height)
                        .animation(.easeInOut, value: progress)
                }
            }
        }
    }
    
    @ViewBuilder
    private var bottomAlignmentLayout: some View {
        VStack(spacing: .zero) {
            
            GeometryReader { geometry in
                ZStack(alignment: .leading) {
                    RoundedRectangle(cornerRadius: 16)
                        .stroke(Color.gray.opacity(0.6), lineWidth: 2)
                        .fill(Color.clear)
                        .frame(height: height)
                    
                    RoundedRectangle(cornerRadius: 16)
                        .fill(Color(hex: progressColor))
                        .frame(width: geometry.size.width * progress, height: height)
                        .animation(.easeInOut, value: progress)
                }
            }
            
            Spacer()
                .frame(height: spaceBetween)
            
            Text(text)
                .font(.body)
                .foregroundStyle(Color(hex: textColor))
        }
    }
    
    @ViewBuilder
    private var leadingAlignmentLayout: some View {
        HStack(alignment: .top, spacing: .zero) {
            Text(text)
                .font(.body)
                .foregroundStyle(Color(hex: textColor))
            
            Spacer()
                .frame(width: spaceBetween)
            
            GeometryReader { geometry in
                ZStack(alignment: .leading) {
                    RoundedRectangle(cornerRadius: 16)
                        .stroke(Color.gray.opacity(0.6), lineWidth: 2)
                        .fill(Color.clear)
                        .frame(height: height)
                    
                    RoundedRectangle(cornerRadius: 16)
                        .fill(Color(hex: progressColor))
                        .frame(width: geometry.size.width * progress, height: height)
                        .animation(.easeInOut, value: progress)
                }
            }
        }
    }
    
    @ViewBuilder
    private var trailingAlignmentLayout: some View {
        HStack(alignment: .top, spacing: .zero) {
            
            GeometryReader { geometry in
                ZStack(alignment: .leading) {
                    RoundedRectangle(cornerRadius: 16)
                        .stroke(Color.gray.opacity(0.6), lineWidth: 2)
                        .fill(Color.clear)
                        .frame(height: height)
                    
                    RoundedRectangle(cornerRadius: 16)
                        .fill(Color(hex: progressColor))
                        .frame(width: geometry.size.width * progress, height: height)
                        .animation(.easeInOut, value: progress)
                }
            }
            
            Spacer()
                .frame(width: spaceBetween)
            
            Text(text)
                .font(.body)
                .foregroundStyle(Color(hex: textColor))
        }
    }
    
    @ViewBuilder
    private var content: some View {
        switch alignment {
        case .top, .center: topAlignmentLayout
        case .bottom:  bottomAlignmentLayout
        case .left: leadingAlignmentLayout
        case .right: trailingAlignmentLayout
        }
    }
    
    var body: some View {
        content
    }
}
